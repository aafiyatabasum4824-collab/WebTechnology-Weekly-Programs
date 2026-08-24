<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">

        <html>
            <head>
                <title>Book Catalog</title>

                <style>
                    table {
                        border-collapse: collapse;
                        width: 80%;
                    }

                    th {
                        background-color: green;
                        color: white;
                    }

                    th, td {
                        border: 1px solid black;
                        padding: 10px;
                        text-align: center;
                    }

                    h2 {
                        color: green;
                    }
                </style>
            </head>

            <body>

                <h2>Book Information</h2>

                <table>
                    <tr>
                        <th>Course</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Publisher</th>
                        <th>Pages</th>
                        <th>Price</th>
                    </tr>

                    <xsl:for-each select="catalog/book">

                        <tr>
                            <td>
                                <xsl:value-of select="@course"/>
                            </td>

                            <td>
                                <xsl:value-of select="title"/>
                            </td>

                            <td>
                                <xsl:value-of select="author"/>
                            </td>

                            <td>
                                <xsl:value-of select="publisher"/>
                            </td>

                            <td>
                                <xsl:value-of select="pages"/>
                            </td>

                            <td>
                                <xsl:value-of select="price"/>
                            </td>
                        </tr>

                    </xsl:for-each>

                </table>

            </body>
        </html>

    </xsl:template>

</xsl:stylesheet>
