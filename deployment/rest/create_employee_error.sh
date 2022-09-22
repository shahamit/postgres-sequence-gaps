curl --location --request POST 'http://localhost:8080/employees/create-employee' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "John"
}'