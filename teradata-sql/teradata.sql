-- 1. Find the user with the email address of john-smith1992@gmail.com and return their user data.

SELECT *
FROM user
WHERE email = 'john-smith1992@gmail.com';

-- 2. Assuming that the user ID is “2921-299-1929182”, find out how many clicks this user has made within the app throughout the entire history.
SELECT SUM(clicks) AS total_clicks
FROM app_usage
WHERE user_id = '2921-299-1929182';


-- 3. Find out how many different devices this user has used within the app.

SELECT COUNT(DISTINCT device) AS distinct_devices_used
FROM app_usage
WHERE user_id = '2921-299-1929182';

-- 4. Return all the transactions of this user.

SELECT *
FROM transactions
WHERE sender_id = '2921-299-1929182' OR recipient_id = '2921-299-1929182';

-- 5. Now return all the transactions of this user within the last 30 days.
SELECT *
FROM transactions
WHERE (sender_id = '2921-299-1929182' OR recipient_id = '2921-299-1929182')
AND timestamp > CURRENT_DATE - INTERVAL '30 DAYS';

-- 6. Return any transactions with a status equal to “FAILED.”
SELECT *
FROM transactions
WHERE status = 'FAILED';

-- 7. Calculate the total number of transactions sent by each email address within the database in descending order concerning the number of transactions.

SELECT u.email, COUNT(t.id) AS total_transactions
FROM user u
JOIN transactions t ON u.id = t.sender_id
GROUP BY u.email
ORDER BY total_transactions DESC;



