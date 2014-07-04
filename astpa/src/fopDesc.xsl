<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- author: Jaqueline Patzek, Patrick Wickenhäuser,Lukas Balzer -->
	<!-- StuPro 2013 / 2014 -->
	
	<!-- Note: This xsl gathers all the informations from the .haz-file -->
	<!-- which is generated by the "prepareForExport()"-Command in the  -->
	<!-- ViewContainer.java -->
    <xsl:import href="ucaTableTemp.xsl"/>
	<xsl:template match="/*">
		<fo:root>
			<!-- Page layout -->
			<fo:layout-master-set>

				<!-- Page-Master for the front-page -->
				<fo:simple-page-master margin="5mm 5mm 10mm 5mm"
					page-height="297mm" page-width="210mm" master-name="A4">
					<fo:region-body margin="15mm 0mm 10mm 5mm" />
					<fo:region-before extent="20mm" display-align="before" />
					<fo:region-after extent="10mm" display-align="after" />
				</fo:simple-page-master>

				<!-- Page-Master for the common pages -->
				<fo:simple-page-master margin="5mm 5mm 5mm 5mm"
					page-height="297mm" page-width="210mm" master-name="titel">
					<fo:region-body margin="5mm 0mm 10mm 5mm" />
				</fo:simple-page-master>

			</fo:layout-master-set>
			
			<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
			<!-- ++++++++++++++++++++ START OF PDF ++++++++++++++++++++ -->
			<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
			
			<fo:page-sequence master-reference="titel">
				<fo:flow flow-name="xsl-region-body">
					<!-- Main-data for the front-page -->
					<fo:block intrusion-displace="line">
						<fo:table space-after="30pt" margin="5mm 5mm 10mm 5mm">
							<fo:table-column column-number="1" column-width="25%"
								border-style="none" />
							<fo:table-column column-number="2" column-width="75%"
								border-style="none" />
							<fo:table-body>
								<fo:table-row border="1pt solid black">
									<fo:table-cell padding="4px">
										<fo:block font-size="12pt" font-weight="bold">
											Title
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="4px">
										<fo:block font-size="12pt">
											<xsl:value-of select="projectdata/projectName" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row border="1pt solid black">
									<fo:table-cell padding="4px">
										<fo:block font-size="12pt" font-weight="bold">
											Date and Time
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="4px">
										<fo:block font-size="12pt">
											<xsl:value-of select="exportinformation/date" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<xsl:if test="exportinformation/company">
									<fo:table-row border="1pt solid black">
										<fo:table-cell padding="4px">
											<fo:block font-size="12pt" font-weight="bold">
												Company
											</fo:block>
										</fo:table-cell>
										<fo:table-cell padding="4px">
											<fo:block font-size="12pt">
												<xsl:value-of select="exportinformation/company" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:if>
								<fo:table-row border="1pt solid black">
									<fo:table-cell padding="4px">
										<fo:block font-size="12pt" font-weight="bold">
											Description
										</fo:block>
									</fo:table-cell>
									<fo:table-cell padding="4px">
										<fo:block font-size="12pt">
											<xsl:value-of select="projectdata/projectDescription" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>

			
			
			<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
			<!-- +++++++++++++++++++++ END OF PDF +++++++++++++++++++++ -->
			<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

		</fo:root>
	</xsl:template>
    


</xsl:stylesheet>


