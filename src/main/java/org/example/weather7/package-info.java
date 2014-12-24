/**
 * 
 */
/**
 * @author huongnt
 *
 */

@Application
@Portlet
@Servlet("/weather7/*")
@Assets(stylesheets = @Stylesheet(src = "/org/example/assets/bootstrap.css"))
package org.example.weather7;

import juzu.plugin.servlet.Servlet;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.portlet.Portlet;
import juzu.Application;

