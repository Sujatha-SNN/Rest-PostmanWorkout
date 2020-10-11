
# Rest-PostmanWorkout 
Contains API testscripts  in postman and Rest Assured for scenarios based on servicenow,amio,paystack,freshbooks,viber.
src/test contains the following scenario of paystack implemented in REST APIcustom Framework using Cucumber (BDD Framework).
1. Create payment page on paystack - use oauth2.0 for authorization using the client id and client secret.
2. Get all payments pages and verify the newly created page detail.(using Hamcrest and assertion)
3. Get the newly created payments page and check the details.(Using Hamcrest and assertion)
