package com.btucall;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    
    
    /**
     * Método para limpar os campos da tela
     * @param view
     */
    public void limparCampos(View view) {
    	EditText editMetros = (EditText) this.findViewById(R.id.editTextMetros);
    	editMetros.setText("");
    	
    	EditText editPessoas = (EditText) this.findViewById(R.id.editTextPessoas);
    	editPessoas.setText("");  	
    	
    	EditText editEquipamentos = (EditText) this.findViewById(R.id.editTextEquipamentos);
    	editEquipamentos.setText("");       	
    	
    }
    
    /**
     * Método usado para calcular os BTUS    
     * @param view
     */
    public void calcular(View view) {
    	
    	//Recupera os valores da tela
    	EditText editMetros = (EditText) this.findViewById(R.id.editTextMetros);
    	String metros = editMetros.getText().toString();
    	
    	EditText editPessoas = (EditText) this.findViewById(R.id.editTextPessoas);
    	String pessoas = editPessoas.getText().toString();    	
    	
    	EditText editEquipamentos = (EditText) this.findViewById(R.id.editTextEquipamentos);
    	String equipamentos = editEquipamentos.getText().toString();  
    	
    	CheckBox isoladaOUCoberturaCB = (CheckBox) this.findViewById(R.id.checkBoxIsoladoCobertura);
    	String isoladaOUCobertura = isoladaOUCoberturaCB.getText().toString();      	
    	
    	//Variaveis inteiras
    	Integer lMetros = 0;
    	Integer lPessoas = 0;
    	Integer lEquipamentos = 0;
    	
    	String erroMessage = "";
    	
    	//Transforma em valores inteiros para realizar o calculo e valida os obrigatórios
    	if (!metros.equals("")){
    		lMetros =  Integer.valueOf(metros);
    	}else{
    		erroMessage = erroMessage + "metragem";
    	}
    	
    	if (!pessoas.equals("")){
    		lPessoas =  Integer.valueOf(pessoas);
    	}else{
    		if (!erroMessage.equals("")){
    			erroMessage = erroMessage + " e pessoas";
    		}else{
    			erroMessage = "pessoas";
    		}
    	}
    	
    	if (!equipamentos.equals("")){
    		lEquipamentos =  Integer.valueOf(equipamentos);
    	}
    	
    	
    	//Validar campos obrigatórios e exibe mesagem para o usuário
    	if (!erroMessage.equals("")){
        	AlertDialog.Builder mensagem = new AlertDialog.Builder(MainActivity.this);  
            mensagem.setTitle("Atenção");  
            mensagem.setMessage("Campos Obrigatórios: " + erroMessage);  
            mensagem.setNeutralButton("OK", null);  
            mensagem.show();  
            return;
    	}
  	
    	
    	//Variavel que armazena o total
    	Integer total = 0;
    	
    	//Caso exista mais de duas pessoas, cada pessoa exttra soma mais 600
    	if(lPessoas > 2){
    		total = total  + (600 * (lPessoas - 2));
    	}
    	
    	//cada equipamento exttra soma mais 600
    	if(lEquipamentos > 0){
    		total = total + (600 * lEquipamentos);
    	}
    	
    	//Verifica se é casa com isolação ou cobertura. Caso seja isolada ou cobertura multiplica por 800, caso contrario por 600
    	if (isoladaOUCoberturaCB.isChecked()){
    		total = total + (800 * lMetros);
    	}else{
    		total = total + (600 * lMetros);
    	}
    	
    	//Atualiza a Resposta na tela
    	TextView resposta = (TextView) this.findViewById(R.id.textResultado);
    	resposta.setText( total.toString() +  " BTUs");
    	
    	
    	
    	System.out.println("Metros >>>>>>>> " +metros);
    	System.out.println("Pessoas >>>>>>>> " +pessoas);
    	System.out.println("Equipamentos >>>>>>>> " +equipamentos);
    	System.out.println("IsoladaOUCobertura >>>>>>>> " +isoladaOUCoberturaCB.isChecked());
    	System.out.println("textResultado >>>>>>>> " +total.toString());
    	
    }    
    

}
