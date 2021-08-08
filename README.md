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


# TODO list:
* cc and bcc need to support email list, currently only support single email
* Missing many test cases, run out of time.
* API Document should be moved to a API document tool like SwaggerHub
* Need to add more error handling on top of above handling errors.
* Add Logging using Lombok

