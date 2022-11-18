package my.chtholly.one.shop.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutPutExcel {
    /**
     * 导出 Excel 文件, 使用 Object 可以不局限与只能导出一种对象类型的尴尬
     * @param obj 表示需要导出的对象
     */
    public static void outPutExcelMethod(List obj) {
        // 默认导出地址 obj.getClass().getName() 使用反射获取类的名称
        String fileName = "D:\\1\\"+obj.get(0).getClass().getSimpleName()+".xlsx";
        System.out.println("Excel文件: "+obj.get(0).getClass().getSimpleName()+".xlsx 开始生成");

        // 创建一个输出流，将文件输出
        FileOutputStream outputStream = null;

        // 因为 write 需要 List 作为参数，所以可以直接将数据存储与 List 内
        List dateList= obj;

        try {
            outputStream = new FileOutputStream(fileName);
            // EasyExcel.write(outputStream) 为了使用流将数据导出
            ExcelWriter writer = EasyExcel.write(outputStream).build();
            // writerSheet 中 sheetNo 表示导出的是 Excel 中第几页数据，sheetName 表示导出的该页数据名称
            // head 表示 Excel 数据需要映射的类
            WriteSheet sheet = EasyExcel.writerSheet(0, "Excel表第0页的名称").head(obj.get(0).getClass()).build();

            // 开始写入数据，
            writer.write(obj, sheet);
            // 刷新数据
            writer.finish();
            outputStream.flush();
            System.out.println("Excel文件生成成功 ---> 生成位置: "+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null){
                    // 关闭流
                    outputStream.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


}
