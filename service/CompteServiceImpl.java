package epita.projet_bensadi_vincent.service;
// ...existing imports...

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    /**
     * Constructeur du service CompteServiceImpl.
     * @param compteRepository Repository compte
     * @param compteMapper Mapper compte
     */
    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    /**
     * Crédite un compte avec le montant spécifié.
     * <p>Vérifie que le compte est actif et que le montant est positif.</p>
     * @param numeroCompte Numéro du compte à créditer
     * @param montant Montant à créditer
     * @return DTO du compte mis à jour
     */
    @Override
    public CompteDto crediter(String numeroCompte, java.math.BigDecimal montant) {
        // ...existing code...
    }

    /**
     * Débite un compte avec le montant spécifié.
     * <p>Vérifie que le compte est actif, que le montant est positif et que le découvert n'est pas dépassé.</p>
     * @param numeroCompte Numéro du compte à débiter
     * @param montant Montant à débiter
     * @return DTO du compte mis à jour
     */
    @Override
    public CompteDto debiter(String numeroCompte, java.math.BigDecimal montant) {
        // ...existing code...
    }
}

