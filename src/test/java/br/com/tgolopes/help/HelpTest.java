package br.com.tgolopes.help;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

/**
 * 
 * @author Thiago Oliveira Lopes
 *
 */
public class HelpTest {
	
	public static SetContentTypeResultHandler setContentType(String contentType) {
		return new SetContentTypeResultHandler(contentType);
	}

	private static class SetContentTypeResultHandler implements ResultHandler {
		private String contentType;

		private SetContentTypeResultHandler(String contentType) {
			this.contentType = contentType;
		}

		@Override
		public void handle(MvcResult result) throws Exception {
			result.getResponse().setContentType(contentType);
		}
	}
}
