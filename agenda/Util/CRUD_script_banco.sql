/* CRUD CREATE */
use dbagenda;
insert into contatos (nome,fone,email)
values ('Bill Gates', '99999-1111','bill@outlook.com');

/* CRUD READ */
select * from contatos;
select * from contatos
where idcon = 5;


/* CRUD UPDATE */
update contatos
set nome='', fone='', email=''
where idcon = 5;