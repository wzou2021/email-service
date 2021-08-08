# Email Service API Document

*  API: /api/v1/sendEmail
* Method:POST
* Authentication: NO 
* Response Schemas : 500(INTERNAL_SERVER_ERROR), 400(BAD_REQUEST), 401(UNAUTHORIZED) etc.
* Payload Example : 
 ```json
{
    "from":"test1@gmail.com",
    "to":[
        "test2@gmail.com","test3@gmail.com"
    ], 
    "cc":"test4@gmail.com",
    "bcc":"test5@gmail.com",
    "subject":"Mail Gun Testing",
    "text":"emailTest"
  }
```
* Success Response Example (For SendGrip = 202 ACCEPTED, For MailGun = 200 OK)
```json
{
    "message": "Send Email success via mailGun",
    "details": []
}
```
* Failed Response Example:
```json
{
    "message": "faild",
    "details": "Timeout "
}
```

* The executable jar has been deployed into below location via AWS Elastic Beanstalk
* Version:
  email-service-1.0.1.jar

# Installation Guide
* modify email.properties
* mvn install
* java -jar email-service-1.x.0.jar
* 
# FEATURE list:
* Support input validation including Empty check and Email Format validation
* In Configuration File, emailProvider1.primary=true, means this email provider will be run first, and switch to the email provider with primary=false. 

# TODO list:(Due to time limit, below needs to be improved)
* Missing many test cases, run out of time.
* API Document should be moved to a API document tool like SwaggerHub
* Need to add more error handling on top of above handling errors.
* Add Logging using Lombok
* Add detailed error message returned from email provider API, fix bug with response status code.
* cc and bcc need to support email list, currently only support single email

# Cloud Deployment
* Deployed to AWS 
* URL http://webserviceexample-env.eba-y2z3ppjc.ap-southeast-2.elasticbeanstalk.com/api/v1/sendEmail
* NOTE: Properies file contains my test account information, so it only works for below example payload
* JSON for sending to mailGun successfully
```json
{
    "from":"wenjingzou@gmail.com",
    "to":[
        "hongbinbiz@gmail.com","hongbinbiz05@gmail.com"
    ], 
    "subject":"Mail Gun Testing",
    "text":"emailTest"
}
```
* JSON for sending sendGrip successfully
```json
{
    "from":"hongbinbiz@gmail.com",
    "to":[
        "wenjingzou@gmail.com","hongbinbiz05@gmail.com"
    ], 
    "cc":"test@gmail.com",
    "bcc":"test1@gmail.com",
    "subject":"Mail Gun Testing",
    "text":"emailTest"
}
```
