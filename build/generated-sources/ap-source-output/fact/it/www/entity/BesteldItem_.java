package fact.it.www.entity;

import fact.it.www.entity.Bestelling;
import fact.it.www.entity.Gerecht;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-14T08:37:56")
@StaticMetamodel(BesteldItem.class)
public class BesteldItem_ { 

    public static volatile SingularAttribute<BesteldItem, Integer> aantal;
    public static volatile SingularAttribute<BesteldItem, Bestelling> bestelling;
    public static volatile SingularAttribute<BesteldItem, Long> id;
    public static volatile SingularAttribute<BesteldItem, Double> toegepastePrijs;
    public static volatile SingularAttribute<BesteldItem, Gerecht> gerecht;

}