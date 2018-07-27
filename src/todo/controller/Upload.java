
package todo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  Part part = request.getPart("file");//送信されたデータ取得
    	  String fileName = extractFileName(part);
    	  part.write("/UploadServlet/upload/" + fileName);//保存先パス+ファイル名
    	}

    	/**
    	* ファイル名を取り出す
    	* @param part パート
    	* @return ファイル名
    	*/
    	private String extractFileName(Part part) {
    	  // System.out.println(part.getHeader("Content-Disposition"));
    	  // これが出力される-> form-data; name="file"; filename="pic_278.png"
    	  String[] splitedHeader = part.getHeader("Content-Disposition").split(";");

    	  String fileName = null;
    	  for(String item: splitedHeader) {
    	      if(item.trim().startsWith("filename")) {
    	          fileName = item.substring(item.indexOf('"')).replaceAll("\"", "");
    	      }
    	  }
    	  return fileName;
    	}
}