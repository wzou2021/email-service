# Email Service API Document

* API document:
Servers: http://microserviceexample.eba-nt3jhu8m.ap-southeast-2.elasticbeanstalk.com/api/v1/sendEmail
* Method:POST
* Authentication: NO
* Response Schemas : 
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
* The executable jar has been deployed into below location via AWS Elastic Beanstalk
* Version:
  email-service-1.0.1.jar

# FEATURE list:
* Support input validation including Empty check and Email Format validation


# TODO list:
* cc and bcc need to support email list, currently only support single email
* Missing many test cases
* 
