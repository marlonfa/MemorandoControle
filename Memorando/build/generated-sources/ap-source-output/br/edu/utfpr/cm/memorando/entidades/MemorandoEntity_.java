package br.edu.utfpr.cm.memorando.entidades;

import br.edu.utfpr.cm.memorando.entidades.RemetenteEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-05T09:53:17")
@StaticMetamodel(MemorandoEntity.class)
public class MemorandoEntity_ { 

    public static volatile SingularAttribute<MemorandoEntity, Long> id;
    public static volatile SingularAttribute<MemorandoEntity, String> conteudo;
    public static volatile SingularAttribute<MemorandoEntity, Date> data;
    public static volatile SingularAttribute<MemorandoEntity, String> assunto;
    public static volatile SingularAttribute<MemorandoEntity, String> nomeDestinatario;
    public static volatile SingularAttribute<MemorandoEntity, RemetenteEntity> remetente;

}