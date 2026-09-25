package br.com.herbario.controller;

import br.com.herbario.model.Planta;
import br.com.herbario.repository.PlantaRepository;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PlantaController {
    private final PlantaRepository repository;

    public PlantaController(PlantaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String listar(@RequestParam(defaultValue = "") String busca, Model model) {
        String termo = busca.trim();
        List<Planta> plantas = termo.isEmpty() ? repository.findAllByOrderByNomeAsc()
            : repository.findByNomeContainingIgnoreCaseOrNomeCientificoContainingIgnoreCaseOrderByNomeAsc(termo, termo);
        model.addAttribute("plantas", plantas);
        model.addAttribute("busca", termo);
        model.addAttribute("total", repository.count());
        return "index";
    }

    @GetMapping("/plantas/nova")
    public String novo(Model model) {
        model.addAttribute("planta", new Planta());
        model.addAttribute("editando", false);
        return "formulario";
    }

    @PostMapping("/plantas")
    public String criar(@Valid @ModelAttribute("planta") Planta planta, BindingResult erros,
                        @RequestParam(value = "imagemArquivo", required = false) MultipartFile imagemArquivo,
                        Model model, RedirectAttributes mensagens) {
        if (erros.hasErrors()) {
            model.addAttribute("editando", false);
            return "formulario";
        }
        guardarImagem(planta, imagemArquivo);
        Planta salva = repository.save(planta);
        mensagens.addFlashAttribute("mensagem", "Planta cadastrada com sucesso.");
        return "redirect:/plantas/" + salva.getId();
    }

    @GetMapping("/plantas/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        model.addAttribute("planta", encontrar(id));
        return "detalhes";
    }

    @GetMapping("/plantas/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("planta", encontrar(id));
        model.addAttribute("editando", true);
        return "formulario";
    }

    @PostMapping("/plantas/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("planta") Planta dados,
                            BindingResult erros,
                            @RequestParam(value = "imagemArquivo", required = false) MultipartFile imagemArquivo,
                            Model model, RedirectAttributes mensagens) {
        Planta atual = encontrar(id);
        if (erros.hasErrors()) {
            dados.setId(id);
            model.addAttribute("editando", true);
            return "formulario";
        }
        atual.setNome(dados.getNome());
        atual.setNomeCientifico(dados.getNomeCientifico());
        atual.setTipo(dados.getTipo());
        atual.setToxicidade(dados.getToxicidade());
        atual.setUso(dados.getUso());
        atual.setDescricao(dados.getDescricao());
        atual.setOrigem(dados.getOrigem());
        atual.setHabitat(dados.getHabitat());
        atual.setParteUtilizada(dados.getParteUtilizada());
        atual.setCultivo(dados.getCultivo());
        atual.setCuriosidades(dados.getCuriosidades());
        guardarImagem(atual, imagemArquivo);
        repository.save(atual);
        mensagens.addFlashAttribute("mensagem", "Registro atualizado com sucesso.");
        return "redirect:/plantas/" + id;
    }

    @PostMapping("/plantas/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes mensagens) {
        repository.delete(encontrar(id));
        mensagens.addFlashAttribute("mensagem", "Planta excluída do herbário.");
        return "redirect:/";
    }

    private Planta encontrar(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Planta não encontrada"));
    }

    private void guardarImagem(Planta planta, MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            return;
        }
        String tipo = arquivo.getContentType();
        if (tipo == null || !tipo.startsWith("image/")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Escolha um arquivo de imagem válido.");
        }
        if (arquivo.getSize() > 4_000_000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A imagem deve ter no máximo 4 MB.");
        }
        try {
            String conteudo = Base64.getEncoder().encodeToString(arquivo.getBytes());
            planta.setImagem("data:" + tipo + ";base64," + conteudo);
        } catch (IOException erro) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível ler a imagem.", erro);
        }
    }
}
