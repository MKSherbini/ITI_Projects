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
                    <!--                <xsl:apply-templates/>-->
                    <xsl:for-each select="CATALOG/CD">
                        <xsl:sort select="COUNTRY" />
                        <xsl:if test="YEAR &gt; 1990">
                            <xsl:apply-templates select="."/>
                        </xsl:if>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="CD" >
        <tr>
            <xsl:apply-templates select="TITLE"/>
            <xsl:apply-templates select="ARTIST"/>
            <xsl:apply-templates select="PRICE"/>
            <xsl:apply-templates select="COUNTRY"/>
        </tr>

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
<!--<?xml version="1.0"?>-->

<!--<xsl:stylesheet version="1.0"-->
<!--                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">-->

<!--    <xsl:template match="/">-->
<!--        <html>-->
<!--            <body>-->
<!--                <h2>My CD Collection</h2>-->
<!--                <table border="1">-->
<!--                    <tr bgcolor="#9acd32">-->
<!--                        <th>Title</th>-->
<!--                        <th>Artist</th>-->
<!--                        <th>Price</th>-->
<!--                        <th>Country</th>-->
<!--                        <th>Year</th>-->
<!--                    </tr>-->
<!--&lt;!&ndash;                <xsl:apply-templates select="CATALOG/CD"/>&ndash;&gt;-->
<!--                    <xsl:for-each select="CATALOG/CD">-->
<!--                        <xsl:if test="YEAR &gt; 1990">-->
<!--                        <xsl:sort select="COUNTRY" />-->
<!--                        <tr>-->
<!--&lt;!&ndash;                            <xsl:apply-templates select="TITLE"/>&ndash;&gt;-->
<!--                            <td><xsl:value-of select="TITLE"/></td>-->
<!--                            <td><xsl:value-of select="ARTIST"/></td>-->
<!--                            <td><xsl:value-of select="PRICE"/></td>-->
<!--                            <td><xsl:value-of select="COUNTRY"/></td>-->
<!--                            <td><xsl:value-of select="YEAR"/></td>-->
<!--                        </tr>-->
<!--                        </xsl:if>-->
<!--                    </xsl:for-each>-->
<!--                </table>-->
<!--            </body>-->
<!--        </html>-->
<!--    </xsl:template>-->

<!--&lt;!&ndash;    <xsl:template match="CATALOG/CD" >&ndash;&gt;-->
<!--&lt;!&ndash;&lt;!&ndash;        <p>&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;&lt;!&ndash;            <xsl:apply-templates select="TITLE"/>&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;&lt;!&ndash;            <xsl:apply-templates select="ARTIST"/>&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;&lt;!&ndash;        </p>&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;    </xsl:template>&ndash;&gt;-->

<!--&lt;!&ndash;    <xsl:template match="TITLE">&ndash;&gt;-->
<!--&lt;!&ndash;&lt;!&ndash;        Title:&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;        <td style="color:#ff0000">&ndash;&gt;-->
<!--&lt;!&ndash;            <xsl:value-of select="."/>&ndash;&gt;-->
<!--&lt;!&ndash;        </td>&ndash;&gt;-->
<!--&lt;!&ndash;    </xsl:template>&ndash;&gt;-->
<!--&lt;!&ndash;    <xsl:template match="ARTIST">&ndash;&gt;-->
<!--&lt;!&ndash;&lt;!&ndash;        Title:&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;        <span style="color:#ff0000">&ndash;&gt;-->
<!--&lt;!&ndash;            <xsl:value-of select="."/>&ndash;&gt;-->
<!--&lt;!&ndash;        </span>&ndash;&gt;-->
<!--&lt;!&ndash;        <br/>&ndash;&gt;-->
<!--&lt;!&ndash;    </xsl:template>&ndash;&gt;-->

<!--</xsl:stylesheet>-->