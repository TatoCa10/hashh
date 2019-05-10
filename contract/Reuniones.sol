pragma solidity ^0.4.16;
contract Reuniones {
      struct Autor{
         	string nombre;
        	string apellido;
                string email;
		string telefono;
       }

       struct File{
           string url;
           string hash;
          address autor;
       }

     mapping (address => Autor) autores;
     mapping (string => File) archivos;
 
     address[] public autoresAccts;
    
     function registrarAutor(address _address, string memory nombre, string memory apellido, string memory email, string memory telefono) public{
        autores[_address].nombre= nombre;
        autores[_address].apellido= apellido;
        autores[_address].email= email;
        autores[_address].telefono= telefono;
        autoresAccts.push(_address);
         
    }

    function certificarArchivo(string memory url,string memory hash, address  autor) public{
        archivos[hash].url=url;
   	archivos[hash].hash=hash;
        archivos[hash].autor=autor;
    }

    
    function obtenerAutores() constant public returns(address[] memory)  {
        return autoresAccts;
    }
    

    function obtenerAutor(address _address)constant public returns (string memory, string memory, string memory, string memory) {
        return (autores[_address].nombre, autores[_address].apellido, autores[_address].email,autores[_address].telefono);
    }

    function obtenerInformacionArchivo(string memory hash) constant public returns (string memory, address ) {
       return (archivos[hash].url, archivos[hash].autor);
    }
    
    function contarAutores() constant public returns (uint) {
        return autoresAccts.length;
    }
   
}

