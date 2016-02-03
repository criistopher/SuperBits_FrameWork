<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "${project.licensePath}">

<#if package?? && package != "">
package ${package};

</#if>
/**
 *
 * @author ${user}
 * @since ${date} 
 * @version 1.0 
 */
public @interface ${name} {

}
