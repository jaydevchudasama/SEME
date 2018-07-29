package seme.vilson.david.com.sems.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import seme.vilson.david.com.sems.adapter.EmployeeList;


public class DashboardActivity extends Activity implements EmployeeList.ItemClickListener
{
    ArrayList<String> arrayList;
    //    ListView listView;
    RecyclerView rv;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar.getBackground().setAlpha(0);
    }

    @Override
    protected void initializeComponents()
    {
        super.initializeComponents();

        rv = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.actionFab);
//                listView = findViewById(R.id.employeeList);

        arrayList = new ArrayList<>();
        arrayList.add("Test1");

        EmployeeList employeeList = new EmployeeList(arrayList);
        rv.setAdapter(employeeList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        employeeList.setClickListener(this);
//        listView.setAdapter(new EmployeeListAdapter(DashboardActivity.this,arrayList));
    }

    @Override
    protected void bindEvents()
    {
        super.bindEvents();
        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.actionFab:
                        Intent intent = new Intent(DashboardActivity.this, EmployeeProfileActivity.class);
                        startActivity(intent);
                        Toast.makeText(DashboardActivity.this, "Add Employee", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        fab.setOnClickListener(clickListener);
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                startActivity(new Intent(DashboardActivity.this,EmployeeProfileActivity.class));
            }
        });
*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position)
    {
        Intent intent = new Intent(DashboardActivity.this, EmployeeProfileActivity.class);
        startActivity(intent);
    }
}
