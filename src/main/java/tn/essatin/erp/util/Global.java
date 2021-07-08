package tn.essatin.erp.util;

public class Global {
	public static final String CNOID = "2.5.29.17";
	public static final String certType = "javax.servlet.request.X509Certificate";
	public static final String login = "login";
	public static final String password = "pass";
	public static final String loginNotFound="Ce compte est introuvable,veuillez verifier le nom d'utilisateur et le mot de passe.";
	public static final String loginNotFound2="Ce compte est introuvable,veuillez verifier votre nom d'utilisateur.";
	public static final String CertNotFound="Aucun certificat n'a été fourni.<br>Veillez fournir un certificat valide!";
	public static final String CetWrong="Le certifica selectionner (ou fournit par votre navigateur) <br> est invalid ( ou correspond a un autre utilisateur)!<br> Vous ne pouvez utilisé cet application qu'a partir un ordinateur certifier.";
	public static final String resetMailSubject = "Demande de récuperation de mot de passe";
	public static final String resetMailCore = "Vous avez demandé une réinitialisation de mot de passe."
			+ "\nUn lien de réinitialisation de mot de passe a été généré pour vous, il sera valide pour une duré de 24h"
			+ "\nSi vous etes a l'origine de cette demande, cliquez sur le lien ci bas, si non ignorer simplement cet e-mail\n\n";
	public static final String roleEtudiant="Etudiant";
	public static final String dataBaseConfig = "git/essat/ESSAT-ERP/dataBaseConfig.yml/";
	public static final String serverdataBaseConfig = "/etc/ESSAT-IN/dataBaseConfig.yml/";
	public static final String ficheDeRenseignementTitle="Fiche de Renseignement";
}