import java.io._
import scala.util.matching.Regex.Match




//Update 08/01/2015 à 22:43 : La fonction est  fonctionnel mais nécessite quelques optimisations !

// In : a string "Score>200;Pays=russie;" Chaque filtre doit finir par ";" même si il y a rien après
//      Liste des opérateurs prit en charge : >= , <= , == , !=   [ Pour L'instant ]
//Out : " TwoDimensional Array "


def stringToArray(filtre :String):Array[Array[String]] = {

  var nbFiltre:Int =filtre.count(_ == ';') // calcule le nombre de ; et donc le nb de filtre
  var tabFiltre = Array.ofDim[String](nbFiltre+1,2) // crée un tableau 2 à dimmensions ou le nombre de ligne = ligneAttr+NbLigneFiltre
  tabFiltre(0)={Array("attribut","op","val")}

  var fullLineArray = filtre.split(";")// split la chaine de charactères = 1 ligne = 1 filtre

  var operatorList="[<!>=]=".r // Liste des opérateurs pris en charge , le .r le rend comme une expréssion REGEX

  for(i <- 1 to nbFiltre)
  {
    var filtreLine=fullLineArray(i-1)
   // print("\n Le filtre : "+fullLineArray(i-1))
    var operatorUsed=operatorList.findFirstIn(filtreLine).getOrElse("Aucun Op trouvé") // On detecte l'opérateur utilisé et avec cet opérateur on va faire un split
   // println("\nLigne n° "+i+" Opérateur détécté : "+operatorUsed)
    var attrAndVar =fullLineArray(i-1).split(operatorUsed)
  //  print(" Attribut : "+attrAndVar(0)+" | Valeur : "+attrAndVar(1))
    tabFiltre(i)={Array(attrAndVar(0),operatorUsed, attrAndVar(1))}

  }

  return tabFiltre
}