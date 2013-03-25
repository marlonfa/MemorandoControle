package br.edu.utfpr.cm.memorando.entidades;

import br.edu.utfpr.cm.memorando.entidades.MemorandoEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T09:23:41")
@StaticMetamodel(AnexoEntity.class)
public class AnexoEntity_ { 

    public static volatile SingularAttribute<AnexoEntity, Long> id;
    public static volatile SingularAttribute<AnexoEntity, MemorandoEntity> memorando;
    public static volatile SingularAttribute<AnexoEntity, byte[]> arquivo;

}