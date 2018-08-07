# employeeHw


[Department]

 * all (list): GET http://localhost:8080/api/departments/all
 * all (page): GET http://localhost:8080/api/departments
 * add department: POST http://localhost:8080/api/department { "dep_name":"test" }
 * update department (param:id) : PUT http://localhost:8080/api/department/{id} { "dep_name":"test" }
 * delete a department (param:id) : DELETE http://localhost:8080/api/departments/{id}

 [Employee]

 * all (list): GET http://localhost:8080/api/employees/all
 * all (page): GET http://localhost:8080/api/employees
 * add employee: POST http://localhost:8080/api/employee {"address":"test","age":40,"department": {"id": 1},"gender":"M","name":"Wed","phone":"12345678"}
 * update employee (param:id) : PUT http://localhost:8080/api/employee/{id} {"address":"test","age":40,"department": {"id": 1},"gender":"M","name":"Wed","phone":"0003388"}
 * delete a department (param:id) : DELETE http://localhost:8080/api/employees/{id}
 * query by conditioin: GET http://localhost:8080/api/employee/condition?employeeName=Lisa&employeeId=12&age=31&departmentName=開發2科
