insert into persons (username,password,role,email) select 'admin','$2a$10$wJt1YZRudHQhwYPtozJhQuZChm0.LCgfrbN2IPJPF75QWPv4Hpnke','ADMIN','zubatacan@mail.ru' where not exists (select 1 from persons where username='admin');
insert into persons (username,password,role,email) select 'user','$2a$10$9frds8gbekpzTBSYQP/4Hemq3/SAiwIzAmX71tzHBBUrKHQRLmYI2','USER','zubatacan@gmail.com' where not exists (select 1 from persons where username='user');