# AWS::GreengrassV2::Deployment ComponentConfigurationUpdate

## Syntax

To declare this entity in your AWS CloudFormation template, use the following syntax:

### JSON

<pre>
{
    "<a href="#merge" title="Merge">Merge</a>" : <i>String</i>,
    "<a href="#reset" title="Reset">Reset</a>" : <i>[ String, ... ]</i>
}
</pre>

### YAML

<pre>
<a href="#merge" title="Merge">Merge</a>: <i>String</i>
<a href="#reset" title="Reset">Reset</a>: <i>
      - String</i>
</pre>

## Properties

#### Merge

_Required_: No

_Type_: String

_Minimum_: <code>1</code>

_Maximum_: <code>10485760</code>

_Update requires_: [No interruption](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/using-cfn-updating-stacks-update-behaviors.html#update-no-interrupt)

#### Reset

_Required_: No

_Type_: List of String

_Update requires_: [No interruption](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/using-cfn-updating-stacks-update-behaviors.html#update-no-interrupt)
