create table disciplinas (
    id int not null primary key auto_increment,
    nome varchar(100),
    codigo varchar(20),
    curso_id int not null,
    professor_id int not null,
    foreign key (curso_id) references cursos(id),
    foreign key (professor_id) references professores(id)
);