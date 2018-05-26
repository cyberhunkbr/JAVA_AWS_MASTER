# Simple Spring Boot plus micoservice demo application  #

A place to do some spring boot coding exercises.


## Structure ##

The `micoservice` module produces a spring boot application. The `functional-tests` module starts 
the spring boot application defined in the `microservice` module and runs cucumber tests defined in
`functional-tests/src/test/resources/features` to verify behaviour.

== Test the application

Now that the application is running, you can test it. You can use any REST client you wish. 

First you want to see the top level service.

To get the user by login id  

```
$ curl  http://localhost:8080/users?name=cyberhunkbr
{
    "name": "bala gongale",
    "email": null,
    "company": "WIPRO DIGITAL",
    "location": "PUNE",
    "login": "cyberhunkbr",
    "id": "39187198",
    "html_url": "https://github.com/cyberhunkbr"
}


```

== To get the user created on date  soft limit of 3..

```
$ curl  http://localhost:8080/search?createdDate=2018-05-26
{
    "items": [
        {
            "name": null,
            "email": null,
            "company": null,
            "location": null,
            "login": "LJHAAAAA",
            "id": "39641581",
            "html_url": "https://github.com/LJHAAAAA"
        },
        {
            "name": null,
            "email": null,
            "company": null,
            "location": null,
            "login": "android3Intermediate",
            "id": "39644936",
            "html_url": "https://github.com/android3Intermediate"
        },
        {
            "name": null,
            "email": null,
            "company": null,
            "location": null,
            "login": "Linkiline",
            "id": "39643537",
            "html_url": "https://github.com/Linkiline"
        }
    ]
}
```

== To get the users by location soft limit of 3.
```
$ curl  http://localhost:8080/search?location=mumbai
{
    "items": [
        {
            "name": null,
            "email": null,
            "company": null,
            "location": null,
            "login": "slidenerd",
            "id": "5139030",
            "html_url": "https://github.com/slidenerd"
        },
        {
            "name": null,
            "email": null,
            "company": null,
            "location": null,
            "login": "vicky002",
            "id": "5517129",
            "html_url": "https://github.com/vicky002"
        },
        {
            "name": null,
            "email": null,
            "company": null,
            "location": null,
            "login": "kovidgoyal",
            "id": "1308621",
            "html_url": "https://github.com/kovidgoyal"
        }
    ]
}
```
