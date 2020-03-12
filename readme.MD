#  Date diff in days calculator
App to calculate difference in days given two dates

## info
### Application
- The application is built using Java with Spring boot.
- Data Validation is done using Spring Using @ValidDate annotation
- Spring Data dynamodb library us used for writing to dynamodb
-

### Cloud Formation
There are three templates for CFN.
1. dynamodb.yml : create dynamodb table
2. web-server.yml : creates ec2 instance from existing custom AMI - which has maven installed and required dependencencies bundled in *~/.m2* folder to speed
up application startup.
3. cfn.template.yml : combines both stacks to run  as a single stack.

## How to deploy:
The ec2 instance is bootstrapped to clone the git repo and build it using maven before running the springboot jar file in the target folder.
### configuration
- create  `KeyName`:  `uno` for ssh access
- change `accessKey` and `secret` in `application.properties` file under resources to your own credentials

## full stack:
 create stack using `cfn.template.yml` that will create full stack with dynamo db table, ec2 instatnce and security group for ssh and http access.
 this is needed the first time, for subsequent deploys use `web-server.yml` instead so thatt dynamodb table doesn't get destroyed.

### web server only:
use this template to only spin off the web server instance to reuse the existing stack's dynamodb table
  

## How to Test
-  get the webServer resource's public DNS (ipV4) , e.g _ec2-13-211-233-144.ap-southeast-2.compute.amazonaws.com_
- hit the url`http://<SUBDOMAIN>.ap-southeast-2.compute.amazonaws.com/date/difference?fromDate=01.01.1800&toDate=03.03.1881`
this will give you validation error
- hit the url `http://<SUBDOMAIN>.ap-southeast-2.compute.amazonaws.com/date/difference?fromDate=01.01.2020&toDate=03.03.2020`
and this time it will returrnr 60 as days between the dates. You can try dates before 1900, malformed date format to tetst validation
and use a leap yer month to further test the robustness of the solution

### github access details:
to be provided on request
