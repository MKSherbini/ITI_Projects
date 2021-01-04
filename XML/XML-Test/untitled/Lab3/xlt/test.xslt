<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>My CD Collection</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Title</th>
                        <th>Artist</th>
                        <th>Price</th>
                        <th>Country</th>
                    </tr>
                    <xsl:apply-templates select="CATALOG/CD" >
                        <xsl:sort select="COUNTRY" />
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="CD">
        <xsl:if test="YEAR &gt; 1990">
        <tr>
            <xsl:apply-templates select="TITLE"/>
            <xsl:apply-templates select="ARTIST"/>
            <xsl:apply-templates select="PRICE"/>
            <xsl:apply-templates select="COUNTRY"/>
        </tr>
        </xsl:if>
    </xsl:template>

    <xsl:template match="TITLE">
        <td>
            <xsl:value-of select="."/>
        </td>
    </xsl:template>
    <xsl:template match="ARTIST">
        <td>
            <xsl:value-of select="."/>
        </td>
    </xsl:template>
    <xsl:template match="PRICE">
        <xsl:choose>
            <xsl:when test=". &gt; 10">
                <td  style="background-color:#FF0000">
                    <xsl:value-of select="."/>
                </td>
            </xsl:when>
            <xsl:when test=". &gt; 8">
                <td  style="background-color:#0000FF">
                    <xsl:value-of select="."/>
                </td>
            </xsl:when>
            <xsl:otherwise >
                <td  style="background-color:#00FF00">
                    <xsl:value-of select="."/>
                </td>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template match="COUNTRY">
        <td>
            <xsl:value-of select="."/>
        </td>
    </xsl:template>

</xsl:stylesheet>