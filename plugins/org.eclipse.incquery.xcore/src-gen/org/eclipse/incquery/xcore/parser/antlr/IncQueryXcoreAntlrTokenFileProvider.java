/*
* generated by Xtext
*/
package org.eclipse.incquery.xcore.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class IncQueryXcoreAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/incquery/xcore/parser/antlr/internal/InternalIncQueryXcore.tokens");
	}
}
