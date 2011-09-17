package org.org.usurper;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.org.usurper.TestCommons.NullPropertyException;
import org.org.usurper.handlers.additional.ValueObjectPropertyTypeHandler;
import org.org.usurper.handlers.basic.AbstractPropertyTypeHandler;
import org.org.usurper.jpetstoredomain.Account;
import org.org.usurper.jpetstoredomain.Cart;
import org.org.usurper.jpetstoredomain.CartItem;
import org.org.usurper.jpetstoredomain.Category;
import org.org.usurper.jpetstoredomain.Item;
import org.org.usurper.jpetstoredomain.LineItem;
import org.org.usurper.jpetstoredomain.Order;
import org.org.usurper.jpetstoredomain.Product;
import org.org.usurper.setup.UsurperGeneratorSetup;

public class JPetStoreDomainTest extends TestCase {
    public void testDomainObjects() {

        // creating type handlers
        Set<AbstractPropertyTypeHandler> typeHandlers = new HashSet<AbstractPropertyTypeHandler>();
        typeHandlers.add(new ValueObjectPropertyTypeHandler(Account.class, Cart.class, CartItem.class, Category.class, Item.class, LineItem.class, Order.class, Product.class));
        UsurperGeneratorSetup usurperGeneratorSetup = new UsurperGeneratorSetup();
        usurperGeneratorSetup.registerPropertyTypeHandlers(typeHandlers);

        UsurperGenerator<Account> accountUsurper = new UsurperGenerator<Account>(Account.class, usurperGeneratorSetup);
        UsurperGenerator<Cart> cartUsurper = new UsurperGenerator<Cart>(Cart.class, usurperGeneratorSetup);
        UsurperGenerator<CartItem> cartItemUsurper = new UsurperGenerator<CartItem>(CartItem.class, usurperGeneratorSetup);
        UsurperGenerator<Category> categoryUsurper = new UsurperGenerator<Category>(Category.class, usurperGeneratorSetup);
        UsurperGenerator<Item> itemUsurper = new UsurperGenerator<Item>(Item.class, usurperGeneratorSetup);
        UsurperGenerator<LineItem> lineItemUsurper = new UsurperGenerator<LineItem>(LineItem.class, usurperGeneratorSetup);
        UsurperGenerator<Order> orderUsurper = new UsurperGenerator<Order>(Order.class, usurperGeneratorSetup);
        UsurperGenerator<Product> productUsurper = new UsurperGenerator<Product>(Product.class, usurperGeneratorSetup);

        try {
            TestCommons.auditVO(accountUsurper.generateUsurper());
            TestCommons.auditVO(cartUsurper.generateUsurper());
            TestCommons.auditVO(cartItemUsurper.generateUsurper());
            TestCommons.auditVO(categoryUsurper.generateUsurper());
            TestCommons.auditVO(itemUsurper.generateUsurper());
            TestCommons.auditVO(lineItemUsurper.generateUsurper());
            TestCommons.auditVO(orderUsurper.generateUsurper());
            TestCommons.auditVO(productUsurper.generateUsurper());
        } catch (NullPropertyException e) {
            fail();
        }
    }
}
