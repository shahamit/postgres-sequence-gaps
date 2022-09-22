curl --location --request POST 'http://localhost:8080/employees' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Tom"
}'