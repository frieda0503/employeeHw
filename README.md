# employeeHw


[Department]

all (list): GET http://localhost:8080/api/departments/all
all (page): GET http://localhost:8080/api/departments
query by conditioin: GET http://localhost:8080/api/condition?employeeName=Ted&employeeId=1&age=30&departmentName=開發1科
add department: POST http://localhost:8080/api/department { "id": "6", "dep_name":"test" }
update department UPDATE http://localhost:8080/api/department { "id": "6", "dep_name":"test" }
delete a department: Delete http://localhost:8080/api/departments/1
[Employee]

all (list): GET http://localhost:8080/api/employees/all
all (page): GET http://localhost:8080/api/employees
add employee: [待補]
