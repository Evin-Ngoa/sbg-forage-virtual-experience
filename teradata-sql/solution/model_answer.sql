-- Scenario 1
select * from user where email = 'john-smith1992@gmail.com';

-- Scenario 2
select count(*) app_usage where user_id = '2921-299-1929182';

-- Scenario 3
select count(distinct device) as unique_devices where user_id = '2921-299-1929182';

-- Scenario 4
select * from transactions where sender_id = '2921-299-1929182' or recipient_id = '2921-299-1929182';

-- Scenario 5
select * from transactions where (sender_id = '2921-299-1929182' or recipient_id = '2921-299-1929182') and not (timestamp > '2022-06-05' OR timestamp < '2022-05-06');

-- Scenario 6
select * from transactions where (sender_id = '2921-299-1929182' or recipient_id = '2921-299-1929182') and status = 'FAILED'

-- Scenario 7
select user.email, count(*) from transactions left join user on transactions.sender_id = user.id group by email order by count(*) desc;