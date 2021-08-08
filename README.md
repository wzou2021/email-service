# Email Service API Document

* API document:
Servers: http://microserviceexample.eba-nt3jhu8m.ap-southeast-2.elasticbeanstalk.com/api/v1/sendEmail
* Method:POST
* Authentication: NO
* Response Schemas : 
* Payload Example : 
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
  . 
* The executable jar has been deployed into below location via AWS Elastic Beanstalk
* Version:
  email-service-1.0.1.jar

TODO list:
