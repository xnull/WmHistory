package ru.wmtool.web.wmids.tree.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.TreeNode;


import ru.wmtool.security.User;
import ru.wmtool.web.wmids.builder.WmidsHierarchyBuilder;
import ru.wmtool.web.wmids.tree.WmidsHierarchyPresenter;
import ru.wmtool.web.wmids.tree.WmidsHierarchyView;
import ru.wmtool.webmoney.Purse;
import ru.wmtool.webmoney.Wmid;

public class WmidsHierarchyViewImpl extends GenericForwardComposer implements WmidsHierarchyView{
	
	private static final long serialVersionUID = 2768709067041102178L;

	private User user;
	
	private WmidsHierarchyPresenter presenter; 
	
	private WmidsHierarchyBuilder builder;
	
	private Tree wmid_tree;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		user = (User) Executions.getCurrent().getDesktop().getAttribute("user");
		//загрузка иерархии (дерева) вмидов.
		presenter.loadWmids(user);
		System.out.println(user.getName());
		System.out.println(user.getWmids().get(0).getNumber());
		System.out.println(user.getWmids().get(0).getPurses().size());
		System.out.println(user.getWmids().get(0).getPurses().get(0).getWmid());
	}
	
	@Override
	public void showWmids(List<Wmid> wmids) {
		wmid_tree.setModel(createWmidsTreeModel(wmids));
		wmid_tree.setItemRenderer(new WmidsHierarchyRenderer());
	}
	
	private DefaultTreeModel createWmidsTreeModel(List<Wmid> wmids){
		List<DefaultTreeNode> wmidNodes = new ArrayList<DefaultTreeNode>();
		for (Wmid wmid : wmids) {
			DefaultTreeNode wmidNode = new DefaultTreeNode(wmid, new ArrayList<DefaultTreeNode>());
			for (Purse purse : wmid.getPurses()){
				wmidNode.add(new DefaultTreeNode(purse));
			}
			wmidNodes.add(wmidNode);
		}
		DefaultTreeNode root = new DefaultTreeNode(null, wmidNodes);
		DefaultTreeModel model = new DefaultTreeModel(root);
	
		return model;
		
	}

	@Override
	public void setPresenter(WmidsHierarchyPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setWmidsHierarchyBuilder(WmidsHierarchyBuilder builder) {
		this.builder = builder;
	}

}
