 create table marca (
       marca_id int8 not null,
        nome varchar(255),
        primary key (marca_id)
    )
    
      create table patrimonio (
       numero_tombo int8 not null,
        descricao varchar(255),
        nome varchar(255),
        marca_id_marca_id int8,
        primary key (numero_tombo)
    ) 
    
     create table perfis (
       usuario_usuario_id int8 not null,
        perfis int4
    )
    
    create table usuario (
       usuario_id int8 not null,
        email varchar(255),
        nome varchar(255),
        senha varchar(255),
        primary key (usuario_id)
    )
    
     alter table if exists patrimonio 
       add constraint FKbk104nl6sdhrl5mw73msjvar7 
       foreign key (marca_id_marca_id) 
       references marca
       
        alter table if exists perfis 
       add constraint FK5kab8jjyqan5im5y37di23dx0 
       foreign key (usuario_usuario_id) 
       references usuario