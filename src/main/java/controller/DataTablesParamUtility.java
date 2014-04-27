package controller;

import javax.servlet.http.HttpServletRequest;

public class DataTablesParamUtility {

    /**
     * creates parameters for DataTable from the specified request
     *
     * @param request the HttpServletRequest
     * @return the parameters for DataTable
     */
    public static DataTableParamModel getParameters(HttpServletRequest request) {

        DataTableParamModel parameters = null;

        if (request.getParameter("sEcho") != null && request.getParameter("sEcho") != "") {

            parameters = new DataTableParamModel();

            parameters.sEcho = request.getParameter("sEcho");
            parameters.sSearch = request.getParameter("sSearch");
            parameters.sColumns = request.getParameter("sColumns");
            parameters.iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
            parameters.iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
            parameters.iColumns = Integer.parseInt(request.getParameter("iColumns"));
            parameters.iSortingCols = Integer.parseInt(request.getParameter("iSortingCols"));
            parameters.iSortColumnIndex = Integer.parseInt(request.getParameter("iSortCol_0"));
            parameters.sSortDirection = request.getParameter("sSortDir_0");
        }

        return parameters;
    }

}
