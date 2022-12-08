<?php
class userRequest  {
    private $id;
    private $username;
    private $password;
    private $email;
    private $status;
    private $usertype;

    public function __construct($id, $username, $password,$email, $status, $usertype) {
    $this->id = $id;
    $this->username = $username;
    $this->password = $password;
    $this->email = $email;
    $this->status = $status;
    $this->usertype = $usertype;
    }

    function getId() {
    return $this->id;
    }
    
    function getUsername() {
    return $this->username;
    }

    function getPass() {
        return $this->password;
    }

    function getEmail() {
    return $this->email;
    }

    function getStatus() {
        return $this->status;
    }

    function getType() {
        return $this->usertype;
    }


    function setId($id) {
    $this->id = $id;
    return $this;
    }

    function setUserName($username) {
    $this->username = $username;
    return $this;
    }
    
    function setPass($password) {
        $this->password = $password;
        return $this;
        }
    
    function setEmail($email) {
    $this->email = $email;
    return $this;
    }

    function setStatus($status) {
        $this->status = $status;
        return $this;
    }

    function setType($usertype) {
        $this->usertype = $usertype;
        return $this;
    }
}
?>