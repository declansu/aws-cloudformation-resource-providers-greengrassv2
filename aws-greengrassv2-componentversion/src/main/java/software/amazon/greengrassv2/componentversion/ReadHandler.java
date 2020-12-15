/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package software.amazon.greengrassv2.componentversion;

import software.amazon.awssdk.services.greengrassv2.GreengrassV2Client;
import software.amazon.cloudformation.proxy.AmazonWebServicesClientProxy;
import software.amazon.cloudformation.proxy.HandlerErrorCode;
import software.amazon.cloudformation.proxy.Logger;
import software.amazon.cloudformation.proxy.ProgressEvent;
import software.amazon.cloudformation.proxy.ProxyClient;
import software.amazon.cloudformation.proxy.ResourceHandlerRequest;

public class ReadHandler extends BaseHandlerStd {
    private Logger logger;

    protected ProgressEvent<ResourceModel, CallbackContext> handleRequest(
        final AmazonWebServicesClientProxy proxy,
        final ResourceHandlerRequest<ResourceModel> request,
        final CallbackContext callbackContext,
        final ProxyClient<GreengrassV2Client> proxyClient,
        final Logger logger) {

        this.logger = logger;


        return ProgressEvent.progress(request.getDesiredResourceState(), callbackContext)
                .then(this::validateArnPresent)
                .then(event ->
                        proxy.initiate("AWS-GreengrassV2-ComponentVersion::Read", proxyClient, request.getDesiredResourceState(), callbackContext)
                                .translateToServiceRequest(Translator::translateToReadRequest)
                                .makeServiceCall((awsRequest, client) -> {
                                    try {
                                        return client.injectCredentialsAndInvokeV2(awsRequest, client.client()::describeComponent);
                                    } catch (Exception ex) {
                                        throw ExceptionTranslator.translateToCfnExceptionForCreatedResource(
                                                "DescribeComponent", request.getDesiredResourceState().getArn(), ex);
                                    }
                                })
                                .done(describeComponentResponse -> {
                                    return ProgressEvent.success(Translator.translateFromReadResponse(describeComponentResponse), callbackContext);
                                }));
    }

}