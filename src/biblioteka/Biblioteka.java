package biblioteka;

import java.util.LinkedList;
import java.util.concurrent.LinkedTransferQueue;

import javax.management.RuntimeErrorException;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import biblioteka.interfejs.BibliotekaInterfejs;

public class Biblioteka implements BibliotekaInterfejs {

	private LinkedList<Knjiga> knjige = new LinkedList<Knjiga>();
	
	@Override
	public void dodajKnjigu(Knjiga knjiga) {
		if(knjiga==null) {
			throw new RuntimeException("Knjiga ne sme biti null");
		}
		if(knjige.contains(knjiga)) {
			throw new RuntimeException("Knjiga je vec u biblioteci");
		}
		knjige.add(knjiga);
	}

	@Override
	public void obrisiKnjigu(Knjiga knjiga) {
		if(!knjige.contains(knjiga)) {
			throw new RuntimeException("Knjiga nije u biblioteci");
		}
		knjige.remove(knjiga);
	}

	@Override
	public LinkedList<Knjiga> pronadjiKnjigu(String naslov, long ISBN,
			String izdavac, Autor autor) {
		LinkedList<Knjiga> rezultat = new LinkedList<Knjiga>();
		
		if (naslov==null && ISBN<=0 && izdavac==null && autor==null) 
			throw new RuntimeException("Morate uneti neki kriterijum");
		
		if (ISBN > 0) {
			for (int i = 0; i < knjige.size(); i++) {
				if (knjige.get(i).getISBN()==ISBN) {
					rezultat.add(knjige.get(i));
				}
			}
		}
		
		return rezultat;
	}

}
