create table matriculas (
    id int not null primary key auto_increment,
    aluno_id int not null,
    turma_id int not null,
    foreign key (aluno_id) references alunos(id),
    foreign key (turma_id) references turmas(id)
);