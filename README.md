# Spring Assignment Application API

# Overview

This is a RESTful API built with **Java** and **Spring Boot** to interact with the [OpenFDA Drugs API](https://open.fda.gov/apis/drug/drugsfda/how-to-use-the-endpoint/). It allows:

- Searching FDA drug application records by **substance name**
- Storing selected drug records in a **local MongoDB database and mongodb atlas cluster**

---

# Features

# Search FDA Drug Applications
- Query OpenFDA data using substance name
- Supports pagination (`page` and `size` parameters)

# Store Drug Application Details
- Save application data locally with:
  - `applicationNumber` (used as unique ID)
  - `substanceName`
  - `sponsorName`

---

# Tech Stack

- Java 17
- Spring Boot
- MongoDB
- Maven
- REST Template

---

# Prerequisites

- Java 17
- Maven 3.9.10

---

# Getting Started

# 1. Clone the Repository

git clone https://github.com/wspace-rr95/spring-assigment.git 

cd spring-assignment

# Build and start the appplication in Spring Tool suite

Click Run->Run configurations ->add new configuration select project name and environment tab
Add these to fetch the mongodb uri specific for environments spring.profiles.active to local or prod

# Configuration
Added the environment specific mongodb uri in application-local.yml and application-prod.yml
and server port as 8092

# API End points 

http://localhost:8092/api/drugs/search?substanceName=TICAGRELOR&pageNumber=1&pageSize=10

response:
{
    "pageNumber": 1,
    "pageSize": 10,
    "data": [
        {
            "applicationNumber": "ANDA208508",
            "substanceName": [
                "TICAGRELOR"
            ],
            "sponsorName": "SUNSHINE"
        },
        {
            "applicationNumber": "ANDA208541",
            "substanceName": [
                "TICAGRELOR"
            ],
            "sponsorName": "DR REDDYS"
        },
        {
            "applicationNumber": "ANDA208576",
            "substanceName": [
                "TICAGRELOR"
            ],
            "sponsorName": "ALEMBIC"
        },
        {
            "applicationNumber": "ANDA208584",
            "substanceName": [
                "TICAGRELOR"
            ],
            "sponsorName": "APOTEX"
        },
        {
            "applicationNumber": "ANDA208596",
            "substanceName": [
                "TICAGRELOR"
            ],
            "sponsorName": "MSN"
        },
        {
            "applicationNumber": "ANDA208599",
            "substanceName": [
                "TICAGRELOR"
            ],
            "sponsorName": "PRINSTON INC"
        },
        {
            "applicationNumber": "ANDA208537",
            "substanceName": [
                "TICAGRELOR"
            ],
            "sponsorName": "INVAGEN PHARMS"
        }
    ],
    "recordCount": 7,
    "totalPages": 1
}

http://localhost:8080/api/drugs/save


input:
{
	"applicationNumber": "ANDA208596",
	"substanceName": [
		"TICAGRELOR"
	],
	"sponsorName": "MSN"
}

