<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
  <xsl:template match="/catalog">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <h1>Product List</h1>
        <body>
          <table border="1" width="100%" cellpadding="4">
            <tr align="center">
              <td><font size="5"><b>Code</b></font></td>
				      <td><font size="5"><b>Description</b></font></td>
				      <td><font size="5"><b>Price</b></font></td>
            </tr>
            <xsl:apply-templates select="/catalog/product">
              <xsl:sort select="@code"/>
            </xsl:apply-templates>
          </table>
        </body>
      </head>
    </html>
  </xsl:template>
  <xsl:template match="/catalog/product">
    <tr>
      <td>
        <xsl:value-of select="@code"/>
      </td>
      <td>
        <xsl:value-of select="description"/>
      </td>
      <td>
        $<xsl:value-of select="price"/>
      </td>
    </tr>
  </xsl:template>
</xsl:stylesheet>
