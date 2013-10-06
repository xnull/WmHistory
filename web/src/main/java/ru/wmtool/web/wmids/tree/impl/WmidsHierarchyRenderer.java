package ru.wmtool.web.wmids.tree.impl;

import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import ru.wmtool.webmoney.Purse;
import ru.wmtool.webmoney.Wmid;
/**
 * Рендерер для дерева вмидов.
 * @author nazgul
 *
 */
public class WmidsHierarchyRenderer implements TreeitemRenderer{

	@Override
	public void render(Treeitem item, Object data) throws Exception {
		DefaultTreeNode dtn = (DefaultTreeNode) data;
		if(dtn.getData() instanceof Wmid){
			Wmid wmid = (Wmid) dtn.getData();
			item.setLabel(String.valueOf(wmid.getNumber()));
		} else if(dtn.getData() instanceof Purse){
			Purse purse = (Purse) dtn.getData();
			item.setLabel(purse.getNumber());
		}
		
	}

}
