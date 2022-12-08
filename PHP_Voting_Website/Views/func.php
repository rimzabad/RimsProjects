<?php
session_start();
Class Action {
	private $db;
    
	public function __construct() {
    include ('../Database/Connection.php');
    $this->db = $conn;
	}
	function __destruct() {
	    $this->db->close();
	    ob_end_flush();
	}
    

    function save_user(){   
        extract($_POST);
        $data = " FirstName = '$name' ";
        $data .= ", LastName = '$username' ";
        $data .= ", Gender = '$password' ";
        if(empty($id)){
            $save = $this->db->query("INSERT INTO candidate set ".$data);
        }else{
            $save = $this->db->query("UPDATE candidate set ".$data." where id = ".$id);
        }
        if($save){
            return 1;
        }
}
}

?>