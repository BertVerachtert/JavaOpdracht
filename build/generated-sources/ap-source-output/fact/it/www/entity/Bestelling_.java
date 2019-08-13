package fact.it.www.entity;

import fact.it.www.entity.BesteldItem;
import fact.it.www.entity.Tafel;
import fact.it.www.entity.Zaalpersoneel;
import java.util.GregorianCalendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-14T08:37:56")
@StaticMetamodel(Bestelling.class)
public class Bestelling_ { 

    public static volatile SingularAttribute<Bestelling, GregorianCalendar> datum;
    public static volatile ListAttribute<Bestelling, BesteldItem> besteldeItems;
    public static volatile SingularAttribute<Bestelling, Tafel> tafel;
    public static volatile SingularAttribute<Bestelling, Boolean> betaald;
    public static volatile SingularAttribute<Bestelling, Long> id;
    public static volatile SingularAttribute<Bestelling, Zaalpersoneel> zaalpersoneel;

}