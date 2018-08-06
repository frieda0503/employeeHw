# employeeHw


[Department]

* all (list): GET http://localhost:8080/api/departments/all
* all (page): GET http://localhost:8080/api/departments
* query by conditioin: GET http://localhost:8080/api/condition?employeeName=Ted&employeeId=1&age=30&departmentName=開發1科
* add department: POST http://localhost:8080/api/department { "dep_name":"test" }
* update department (param:id) : PUT http://localhost:8080/api/department/{id} { "dep_name":"test" }
* delete a department (param:id) : DELETE http://localhost:8080/api/departments/{id}

[Employee]

* all (list): GET http://localhost:8080/api/employees/all
* all (page): GET http://localhost:8080/api/employees
* add employee: POST http://localhost:8080/api/employee { "name" : "Emily" , "dep_id" : 1 , "gender" : "Female", "phone" : "0123456789"  , "address" : "testttt" , "age" : 30 }
* update employee (param:id) : PUT http://localhost:8080/api/employee/6 { "name" : "Emily" , "dep_id" : 1 , "gender" : "Female", "phone" : "0123456789"  , "address" : "abcdefg" , "age" : 30 }
* delete a department (param:id) : DELETE http://localhost:8080/api/employees/{id}
