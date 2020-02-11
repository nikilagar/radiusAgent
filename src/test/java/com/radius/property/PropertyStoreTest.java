package com.radius.property;

import com.radius.property.match.PropertyMatcher;
import com.radius.property.search.Query;
import org.junit.Assert;
import org.junit.Test;

public class PropertyStoreTest {
    @Test
    public void matchPropertiesTest() {
        PropertyStore store = new PropertyStore();
        store.addProperty(new Property(1000000, 7, 3, 50.5, 8.2, 1000));
        Query query = new Query(5, 7,  2, 4, 50.5, 8.2, 1000, 2000);
        Property highestMatch = store.matchProperties(query, 1).get(0).getProperty();
        Assert.assertEquals(PropertyMatcher.getMatchPercent(highestMatch, query), 100, 0.001);
    }
}
