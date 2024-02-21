package com.rest.pojo.workspace;

public class workspace_root {
	
	workspace_pojo workspace;
	
	public workspace_root()
	{
	}
	
	public workspace_root(workspace_pojo workspace)
	{
		this.workspace=workspace;
	}

	public workspace_pojo getWorkspace() {
		return workspace;
	}

	public void setWorkspace(workspace_pojo workspace) {
		this.workspace = workspace;
	}
}
