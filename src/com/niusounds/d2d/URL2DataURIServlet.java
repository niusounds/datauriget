package com.niusounds.d2d;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class URL2DataURIServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String url = req.getParameter("url");

		if (url == null || url.isEmpty()) {
			return;
		}

		try {
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			String contentType = conn.getContentType();
			String base64 = Base64.encodeBase64String(IOUtils.toByteArray(conn.getInputStream()));

			resp.setContentType("text/plain");
			resp.getWriter().print("data:");
			resp.getWriter().print(contentType);
			resp.getWriter().print(";base64,");
			resp.getWriter().print(base64);
		} catch (Exception e) {
			return;
		}
	}
}
