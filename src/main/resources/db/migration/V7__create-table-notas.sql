create table notas (
    id int not null primary key auto_increment,
    nota decimal(5,2),
    data_lancamento date,
    matricula_id int not null,
    disciplina_id int not null,
    foreign key (matricula_id) references matriculas(id),
    foreign key (disciplina_id) references disciplinas(id)
);