package org.jboss.resteasy.test.i18n;

import java.util.Locale;

import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.servlet.i18n.Messages;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="ron.sigal@jboss.com">Ron Sigal</a>
 * @version $Revision: 1.1 $
 *
 *          Copyright Aug 29, 2015
 */
public abstract class TestMessagesAbstract extends TestMessagesParent {
    private static final Logger LOG = Logger.getLogger(TestMessagesAbstract.class);
    protected static final String BASE = String.format("0%5s", Messages.BASE).substring(0, 4);

    @Test
    public void testLocale() throws Exception {
        Locale locale = getLocale();
        String filename = "org/jboss/resteasy/plugins/servlet/i18n/Messages.i18n_" + locale.toString() + ".properties";
        if (!before(locale, filename)) {
            LOG.info(getClass() + ": " + filename + " not found.");
            return;
        }

        Assert.assertEquals(getExpected(BASE + "00", "defaultApplicationNotImplemented"),
                Messages.MESSAGES.defaultApplicationNotImplemented());
    }

    @Override
    protected int getExpectedNumberOfMethods() {
        return Messages.class.getDeclaredMethods().length;
    }

    protected abstract Locale getLocale();
}
